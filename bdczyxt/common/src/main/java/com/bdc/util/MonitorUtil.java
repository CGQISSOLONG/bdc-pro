package com.bdc.util;

import org.apache.commons.lang3.StringUtils;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** * 系统信息工具类
 */
public class MonitorUtil {
    private static final Pattern PATTERN = Pattern.compile("(\\d+)");

    public static Map<String, String> getComputerGeneral() {
        HashMap result = null;
        try {
            result = new HashMap(7);
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            Map<String, String> map = System.getenv();
            String computerName = map.get("COMPUTERNAME");
            if (StringUtils.isBlank(computerName)) {
                computerName = address.getHostName();
            }

            Sigar sigar = new Sigar();
            Mem mem = sigar.getMem();
            double memoryTotal = longConversionDouble(mem.getTotal());
            memoryTotal = memoryTotal / 1024.0D / 1024.0D / 1024.0D;
            String memoryString = String.format("%.2f", memoryTotal) + "GB";
            double memUsed = longConversionDouble(mem.getUsed());
            memUsed = memUsed / 1024.0D / 1024.0D / 1024.0D;
            String memUsedString = String.format("%.2f", memUsed) + "GB";
            Properties properties = System.getProperties();
            String systemName = properties.getProperty("os.name");
            String systemArch = properties.getProperty("os.arch");
            String systemVersion = properties.getProperty("os.version");
            result.put("ip", ip);
            result.put("computerName", computerName);
            result.put("memoryString", memoryString);
            result.put("systemName", systemName);
            result.put("systemArch", systemArch);
            result.put("systemVersion", systemVersion);
            result.put("memUsedString", memUsedString);
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        return result;
    }

    public static Map<String, String> getJVMInfo() {
        Runtime runtime = Runtime.getRuntime();
        Properties properties = System.getProperties();
        Map<String, String> map = new HashMap();
        double jvmTotalMemoryDouble = longConversionDouble(runtime.totalMemory());
        jvmTotalMemoryDouble = jvmTotalMemoryDouble / 1024.0D / 1024.0D / 1024.0D;
        double jvmFreeMemoryDouble = longConversionDouble(runtime.freeMemory());
        jvmFreeMemoryDouble = jvmFreeMemoryDouble / 1024.0D / 1024.0D / 1024.0D;
        String jdkPath = properties.getProperty("java.home");
        jdkPath = jdkPath.substring(0, jdkPath.lastIndexOf(properties.getProperty("file.separator")));
        String serverName;
        String serverPath;
        String tomcatPath = System.getProperty("catalina.home");
        String weblogicPath = System.getProperty("weblogic.home");
        if (StringUtils.isBlank(tomcatPath)) {
            serverPath = weblogicPath;
            serverName = "WEBLOGIC";
        } else {
            serverPath = tomcatPath;
            serverName = "TOMCAT";
        }
        Matcher matcher = PATTERN.matcher(properties.getProperty("java.vm.name"));
        String jdkBit = "";
        if (matcher.find()) {
            jdkBit = matcher.group(1);
        }
        if (!StringUtils.isBlank(jdkBit)) {
            jdkBit = "(" + jdkBit + "位)";
        }

        map.put("jvmTotalMemory", String.format("%.2f", jvmTotalMemoryDouble) + "GB");
        map.put("jvmFreeMemory", String.format("%.2f", jvmFreeMemoryDouble) + "GB");
        map.put("processor", runtime.availableProcessors() + "");
        map.put("jdkPath", jdkPath);
        map.put("vmSpeciVersion", properties.getProperty("java.vm.specification.version") + jdkBit);
        map.put("serverName", serverName.toUpperCase());
        map.put("serverPath", serverPath);
        return map;
    }

    public static Map<String, String> getCpuInfo() {
        Sigar sigar = new Sigar();
        HashMap map = new HashMap();
        try {
            CpuInfo[] infos = sigar.getCpuInfoList();
            CpuPerc cpuPerc = sigar.getCpuPerc();
            if (infos.length <= 0) {
                throw new Exception("无cpu");
            }
            map.put("cpuTotal", infos[0].getMhz() + "MHz");
            map.put("cpuVendor", infos[0].getVendor());
            map.put("cpuModel", infos[0].getModel());
            map.put("cpuUser", CpuPerc.format(cpuPerc.getUser()));
            map.put("cpuSystem", CpuPerc.format(cpuPerc.getSys()));
            map.put("cpuCompined", CpuPerc.format(cpuPerc.getCombined()));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return map;
    }

    public static List<Map<String, String>> getHardDisk() {
        Sigar sigar = new Sigar();
        ArrayList list = new ArrayList();

        try {
            FileSystem[] fileSystems = sigar.getFileSystemList();
            for (int i = 0; i < fileSystems.length; ++i) {
                FileSystem fileSystem = fileSystems[i];
                if (fileSystem.getType() == 2) {
                    Map<String, String> map = new HashMap();
                    FileSystemUsage usage = sigar.getFileSystemUsage(fileSystem.getDirName());
                    String diskName = fileSystem.getDevName();
                    if (diskName.indexOf(":") != -1) {
                        diskName = diskName.substring(0, diskName.indexOf(":"));
                        map.put("diskName", diskName);
                        map.put("usageTotal", String.format("%.0f", longConversionDouble(usage.getTotal()) / 1024.0D / 1024.0D) + "GB");
                        map.put("usageFree", String.format("%.1f", longConversionDouble(usage.getFree()) / 1024.0D / 1024.0D) + "GB");
                        map.put("usageUsed", String.format("%.1f", longConversionDouble(usage.getUsed()) / 1024.0D / 1024.0D) + "GB");
                    } else {
                        map.put("diskName", diskName);
                        map.put("usageTotal", String.format("%.2f", longConversionDouble(usage.getTotal()) / 1024.0D / 1024.0D) + "GB");
                        map.put("usageFree", String.format("%.2f", longConversionDouble(usage.getFree()) / 1024.0D / 1024.0D) + "GB");
                        map.put("usageUsed", String.format("%.2f", longConversionDouble(usage.getUsed()) / 1024.0D / 1024.0D) + "GB");
                    }

                    list.add(map);
                }
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return list;
    }

    public static Map<String, String> getComputerInfoRealTime() {
        Map<String, String> map = new HashMap();
        Sigar sigar = new Sigar();

        try {
            Mem mem = sigar.getMem();
            double memUsed = longConversionDouble(mem.getUsed());
            memUsed = memUsed / 1024.0D / 1024.0D / 1024.0D;
            String memUsedFormat = String.format("%.2f", memUsed);
            String memUsedString = memUsedFormat + "GB";
            double memoryTotal = longConversionDouble(mem.getTotal());
            memoryTotal = memoryTotal / 1024.0D / 1024.0D / 1024.0D;
            double memUtilization = memUsed / memoryTotal * 100.0D;
            String memUtilizationFormat = String.format("%.2f", memUtilization);
            map.put("memUsed", memUsedString);
            map.put("memUtilization", memUtilizationFormat);
            CpuPerc cpuPerc = sigar.getCpuPerc();
            map.put("cpuUser", CpuPerc.format(cpuPerc.getUser()));
            map.put("cpuSystem", CpuPerc.format(cpuPerc.getSys()));
            map.put("cpuCompined", CpuPerc.format(cpuPerc.getCombined()));
            Runtime runtime = Runtime.getRuntime();
            double jvmFreeMemoryDouble = longConversionDouble(runtime.freeMemory());
            jvmFreeMemoryDouble = jvmFreeMemoryDouble / 1024.0D / 1024.0D / 1024.0D;
            map.put("jvmFreeMemory", String.format("%.2f", jvmFreeMemoryDouble) + "GB");
            map.put("storageUtilization", getStorageUtilization());
        } catch (Exception var16) {
            var16.printStackTrace();
        }

        return map;
    }

    public static String getStorageUtilization() {
        double fileTotal = 0.0D;
        double fileUsage = 0.0D;

        try {
            Sigar sigar = new Sigar();
            FileSystem[] fileSystems = sigar.getFileSystemList();

            for (int i = 0; i < fileSystems.length; ++i) {
                FileSystem fileSystem = fileSystems[i];
                if (fileSystem.getType() == 2) {
                    FileSystemUsage usage = sigar.getFileSystemUsage(fileSystem.getDirName());
                    fileTotal += longConversionDouble(usage.getTotal()) / 1024.0D / 1024.0D;
                    fileUsage += longConversionDouble(usage.getUsed()) / 1024.0D / 1024.0D;
                }
            }
        } catch (Exception var9) {
        }

        return String.format("%.2f", fileUsage / fileTotal * 100.0D);
    }

    private static double longConversionDouble(long variable) {
        String temp = variable + "";
        double result = Double.parseDouble(temp);
        return result;
    }

    public static void main(String[] args) {
        //System.out.println(getComputerInfoRealTime());
    }
}
