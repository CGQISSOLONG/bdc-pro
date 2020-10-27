package com.admin.controller.bdc;

import com.admin.aop.LogAopAnnotation;
import com.admin.config.Config;

//import com.bdc.repository.primary.MenuRepository;

import com.bdc.common.Constants;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.dao.DictDao;
import com.bdc.dao.MenuDao;
import com.bdc.entity.Menu;
import com.bdc.entity.primary.Dict;
import com.bdc.service.MenuService;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/hjpt/menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private Config config;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private MenuDao menuDao;
    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    @PostMapping(UrlConstant.CREATE)
    @LogAopAnnotation
    public String create(Menu menu) {
        menu.setRank(menu.getParentIds().split(",").length);
        //默认禁用
        menu.setDisabled(Constants.YES);
        //menu.setType(1);
        menuDao.save(menu);
        return "redirect:/hjpt/menu/list";
    }

    /**
     * 加载菜单
     *
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        final List<Menu> all = menuDao.findList();
        model.addAttribute("list", all);
        return "bdc/menu/list";
    }

    /**
     * 添加菜单或者更新菜单
     *
     * @param menuId
     * @param parent
     * @param model
     * @return
     */
    @GetMapping(value = "/form")
    @LogAopAnnotation
    public String form(@RequestParam(value = "menuId", required = false) String menuId,
                       @RequestParam(value = "parent", required = false) boolean parent,
                       @RequestParam(value = "parentId", required = false) Integer parentId,
                       Model model) {
        String url = null;
        final List<Dict> menuTypeList = dictDao.findMenuTypeList();
        if (menuId != null && !parent) {
            final Menu one = menuDao.findOne(Integer.valueOf(menuId));
            model.addAttribute("menu", one);
            model.addAttribute("url", one.getRank() == 1 ? false : true);
            url = "/hjpt/menu/" + menuId + UrlConstant.UPDATE;
            for (int i = 0,j=menuTypeList.size(); i<j;i++) {
                final Dict dict = menuTypeList.get(i);
                if(one.getType().equals(Integer.valueOf(dict.getValue()))){
                    dict.setCheck("selected");
                    break;
                }
            }
        } else {
            url = "/hjpt/menu" + UrlConstant.CREATE;
            if (menuId != null) {
                model.addAttribute("parentPath", menuId);
                model.addAttribute("parentId", parentId);
                model.addAttribute("url", true);
            }
        }

        model.addAttribute("dictList", menuTypeList);
        model.addAttribute("api", url);
        model.addAttribute("upload", "/hjpt/menu/upload");
        return "bdc/menu/form";
    }

    /**
     * 更新菜单
     *
     * @param id
     * @param menu
     * @return
     */
    @PostMapping(value = "/{id}" + UrlConstant.UPDATE)
    @LogAopAnnotation
    public String modify(@PathVariable("id") Integer id, Menu menu, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Menu menuTemp = menuDao.findOne(id);
        menuTemp.setLabel(menu.getLabel());
        menuTemp.setUrl(menu.getUrl());
        menuTemp.setOrderNum(menu.getOrderNum());
        menuTemp.setImageUrl(menu.getImageUrl());

        menuTemp.setType(menu.getType());
      //  menuDao.save(menuTemp);
        menuDao.modify(menuTemp);
        return "redirect:/hjpt/menu/list";
    }


    @PutMapping(value = "/{id}" + UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public void switchStatus(@PathVariable("id") Integer id, @RequestParam("disable") String disable) {
        if (id == null || disable == null) {
            return;
        }
        menuService.updateStateById(id, disable);

    }

    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable("id") Integer id) {
        try {
            menuService.delete(id);
            return ResultUtil.success();
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }

    }


    @PostMapping(value = UrlConstant.UPLOAD, headers = "content-type=multipart/form-data")
    @ResponseBody
    public BaseResult upload(HttpServletRequest request,
                             @RequestParam(value = "file", required = true) MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + new Random().nextInt(20) + "." + "png";
        // 图标上传地址改成项目下
        //当前项目下路径
        File file1 = new File("");
        String filePath = null;
        try {
            //String menumgPath = config.getMenumgPath();
            String menumgPath = file1.getCanonicalPath() + "\\icon\\";
            if (!dosave(file, fileName, menumgPath)) {
                return ResultUtil.error(ResultEnum.UPLOAD_ERROR);
            }
            menumgPath=menumgPath+fileName;
            return ResultUtil.success(menumgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.error(ResultEnum.UPLOAD_ERROR);
    }

    /***
     * 保存图片信息
     * @param file
     * @param fileName
     * @param savePath
     */
    private boolean dosave(MultipartFile file, String fileName, String savePath) {
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        InputStream in = null;
        String saveUrl = savePath + "\\" + fileName;
        try {
            //以流的形式下载文件。
            in = new BufferedInputStream(file.getInputStream());
            OutputStream out = null;
            try {
                int length;
                byte[] buffer = new byte[1024];
                out = new BufferedOutputStream(new FileOutputStream(saveUrl));
                //循环输出
                while ((length = in.read(buffer, 0, 1024)) != -1) {
                    out.write(buffer, 0, length);
                }
                File fileTemp = new File(savePath + "\\" + fileName);
                if (!fileTemp.exists()) {
                    return false;
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return false;
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }

        } catch (IOException ex) {
            log.error("上传图片大小：" + (file != null ? file.getSize() : 0), ex);
            return false;
        }
        return true;
    }

    /**
     * 获取图标图片
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("getImage")
    public void getImage(Integer id, HttpServletRequest request, HttpServletResponse response) {
        String imageUrl = null;
        try {
            imageUrl = menuDao.getOne(id).getImageUrl();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (imageUrl != null) {
            FileInputStream fis = null;
            response.setContentType("image/jpeg");
            response.addHeader("Cache-Control", "max-age=31536000,must-revalidate");
            try {
                OutputStream out = response.getOutputStream();
                File file = new File(imageUrl);
                fis = new FileInputStream(file);
                byte[] b = new byte[fis.available()];
                fis.read(b);
                out.write(b);
                out.flush();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
}
