package com.bdc.controller;

import com.bdc.common.BdcProtection;
import com.bdc.common.PassLogin;
import com.bdc.common.base.BaseResult;
import com.bdc.controller.api.BaseController;
import com.bdc.dao.*;
import com.bdc.entity.Area;
import com.bdc.entity.City;
import com.bdc.entity.Menu;
import com.bdc.entity.Users;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/")
@BdcProtection
@Slf4j
public class indexController extends BaseController {

    @Autowired
    private DictDao dictDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private DjlxDao djlxDao;

    @Value("2020")
    private Integer bdcdjOnlineTime;

    @Value("1997")
    private String cityCodePrefix;

    @RequestMapping(UrlConstant.INDEX)
    public String index(Model model) {
        model.addAttribute("list", dictDao.findMenuTypeList());
        return "bdcs/index";
    }

    @RequestMapping("case_analysis")
    public String case_analysis(Model model) {
        return "bdcxt/case_analysis";
    }

    @RequestMapping("cakeshape")
    public String cake_shape(Model model) {
        model.addAttribute("active", 4);
        model.addAttribute("list", setModel(model));
        return "bdcs/index/cake_shape";
    }

    @RequestMapping("brokenline")
    public String broken_line(Model model) {
        model.addAttribute("active", 3);
        model.addAttribute("list", setModel(model));
        return "bdcs/index/broken_line";
    }

    @RequestMapping("histogram")
    public String histogram(Model model) {
        model.addAttribute("active", 2);
        model.addAttribute("list", setModel(model));
        return "bdcs/index/histogram";
    }
    private List<Area> setModel(Model model) {
        setNums(model);
        City city = cityDao.findCityByCityCodePrefix(cityCodePrefix);
        List<Area> list = Lists.newArrayList();
        final Area area = new Area();
        area.setName(city.getName());
        area.setQxdm("-1");
        list.add(area);
        List<Area> newList = areaDao.findAreaByCityId(Long.valueOf(city.getId()));
        list.addAll(newList);
        return list;
    }
    private void setNums(Model model) {
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        model.addAttribute("year", year);
        model.addAttribute("nums", year - bdcdjOnlineTime);
    }

    @RequestMapping("jsz")
    @ResponseBody
    public BaseResult statistics(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final Users pubUser = (Users) session.getAttribute("user");
        List<Menu> list = (List<Menu>) Menu.buildTree(menuDao.findMenubyUserId2(pubUser.getId(), request.getParameter("type")));
        return ResultUtil.success(list);
    }

    /**
     * 监控监管-登陆监控
     *
     * @return
     */
    @RequestMapping("login_log")
    public String login_log() {
        return "bdcs/jkjg/loginLog";
    }

    /**
     * 监控监管--汇聚异常日志监控
     * @return
     */
    @RequestMapping("hjException_log")
    public String hjException_log(){
        return "bdcs/jkjg/HjExceptionLog";
    }

    /**
     * 监控监管-龙腾查询监控
     *
     * @return
     */
    @RequestMapping("lt_query")
    public String lt_query() {
        return "bdcs/jkjg/ltQuery";
    }

    /**
     * 汇聚接入清单
     * @return
     */
    @RequestMapping("/hjjrqd")
    public String getHjjrqd(Model model) {
        model.addAttribute("djlx", djlxDao.findAll());
        model.addAttribute("dict", dictDao.findQxdmListByCityCodePrefix(cityCodePrefix));
        model.addAttribute("tjfl", dictDao.findTjfl());
        return "bdcs/jkjg/hjjrqd";
    }


    /**
     * 决策分析-汇交一致率
     *
     * @return
     */
    @RequestMapping("hjyzrate")
    public String hjyzrate(Model model, HttpServletRequest request) {
        model.addAttribute("sj", request.getParameter("sj"));
        return "bdcs/hjfx/hjyzrate";
    }

    /**
     * 决策分析-网上申请比例
     *
     * @return
     */
    @RequestMapping("onlineratio")
    public String onlineratio(Model model, HttpServletRequest request) {
        model.addAttribute("sj", request.getParameter("sj"));
        return "bdcs/hjfx/onlineratio";
    }

    /**
     * 决策分析-网上申请发证平均用时
     *
     * @return
     */
    @RequestMapping("averageTime")
    public String averageTime(Model model, HttpServletRequest request) {
        model.addAttribute("sj", request.getParameter("sj"));
        return "bdcs/hjfx/averageTime";
    }

    /**
     * 决策分析-汇交一致率
     *
     * @return
     */
    @RequestMapping("h_hjyzrate")
    public String h_hjyzrate(Model model) {
        setNums2(model);
        model.addAttribute("active", 1);
        return "bdcs/hjfx/h_hjyzrate";
    }

    /**
     * 决策分析-网上申请比例
     *
     * @return
     */
    @RequestMapping("h_onlineratio")
    public String h_onlineratio(Model model) {
        setNums2(model);
        model.addAttribute("active", 2);
        return "bdcs/hjfx/h_onlineratio";
    }

    /**
     * 决策分析-网上申请发证平均用时
     *
     * @return
     */
    @RequestMapping("h_averageTime")
    public String h_averageTime(Model model) {
        setNums2(model);
        model.addAttribute("active", 3);
        return "bdcs/hjfx/h_averageTime";
    }

    /**
     * 平均用时明细
     * @param model
     * @return
     */
    @PassLogin
    @RequestMapping("/getFormYsTotalDataMx")
    public String ybdjfzpjys(Model model,String sj,String qxdm) {
        model.addAttribute("sj", sj);
        model.addAttribute("qxdm", qxdm);
        return "bdcs/hjfx/pjys_ybbdcdjzys_mx";
    }

    /**
     * 累计外网办结总量
     * @param model
     * @return
     */
    @PassLogin
    @RequestMapping("/getFormOnlineWebTotalDataMx")
    public String getFormOnlineWebTotalDataMx(Model model,String sj,String qxdm) {
        model.addAttribute("sj", sj);
        model.addAttribute("qxdm", qxdm);
        return "bdcs/hjfx/ljwwbjl_mx";
    }

    /**
     * 累计办件总量明细
     * @param model
     * @return
     */
    @PassLogin
    @RequestMapping("/getFormOnlineTotalDataMx")
    public String getFormOnlineTotalDataMx(Model model,String sj,String qxdm) {
        model.addAttribute("sj", sj);
        model.addAttribute("qxdm", qxdm);
        return "bdcs/hjfx/ljywbjl_mx";
    }

    /**
     * 数据汇交一致率- 报文接入量明细
     * @param model
     * @param sj
     * @param qxdm
     * @return
     */
    @PassLogin
    @RequestMapping("/getBwjrlMx")
    public String getBwjrlMx(Model model, String sj, String qxdm) {
        model.addAttribute("sj", sj);
        model.addAttribute("qxdm", qxdm);
        return "bdcs/hjfx/bwjrl_mx";
    }

    /**
     * 数据汇交一致率- 其他明细
     * @param model
     * @param sj
     * @param qxdm
     * @return
     */
    @PassLogin
    @RequestMapping("/getSjhjMxOther")
    public String getSjhjMxOther(Model model, String sj, String qxdm) {
        model.addAttribute("sj", sj);
        model.addAttribute("qxdm", qxdm);
        return "bdcs/hjfx/sjhj_mx_other";
    }

    private void setNums2(Model model) {
        Calendar c = Calendar.getInstance();
        //final int nums = c.get(Calendar.MONTH);
        final int nums = 6;
//        model.addAttribute("month", months);
//        model.addAttribute("nums", months - 7);
        /*int nums = 0;
        if (year == 2019) {
            nums = c.get(Calendar.MONTH) - 6;
        } else {
            //nums = (year - 2019) * 12 + c.get(Calendar.MONTH) + 6;
            // 只显示5个月份即可
            nums = 5;
        }*/
        LocalDate today = LocalDate.now();
        final List<String> list = Lists.newArrayList();
        int day = today.getDayOfMonth();
        if(0 != day % 5) {
            today = today.minusDays(day % 5);
        }
        list.add(today.toString().substring(0, 10));
        for (long i = 1L; i < nums; i++) {
            LocalDate localDate = today.minusDays(i * 5);
            String ss = localDate.toString().substring(0, 10);
            list.add(ss);
        }
        model.addAttribute("list", list);
        model.addAttribute("nums", nums);
    }


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
