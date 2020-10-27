package com.admin.controller.bdc;

import com.admin.aop.LogAopAnnotation;
import com.admin.controller.bdc.vo.PageDataVo;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bdc.util.UrlConstant;
import com.bdc.dao.DictDao;
import com.bdc.entity.primary.Dict;
import com.bdc.util.ResultUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 类名：DictController
 * @Description 功能说明：字典管理
 */
@Controller
@Log4j
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictDao dictDao;

    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("api", "/dict/pages");
        return "bdc/dict/list";
    }

    @PostMapping(UrlConstant.CREATE)
    public String create(Dict dict) {
        final Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        dict.setCreateDate(dateString);
        dict.setUpdateDate(dateString);
        dict.setDelFlag("1");
        dict.setParentId("0");
        dictDao.insert(dict);
        return "redirect:/dict/list";
    }

    @PostMapping(UrlConstant.QUERY_PAGE)
    @ResponseBody
    public PageDataVo<Dict> queryPage(HttpServletRequest request) {
        String start = request.getParameter("start");
        String start2 = request.getParameter("order[0][column]");
        String length = request.getParameter("length");
        if (StringUtils.isBlank(start)) {
            start = "0";
        }
        if (StringUtils.isBlank(length)) {
            length = "10";
        }

        PageHelper.startPage(Integer.valueOf(start)/Integer.valueOf(length)+1, Integer.valueOf(length));
        List<Dict> list = dictDao.findList(new Dict());
        PageDataVo<Dict> pageDataVo = new PageDataVo<>();
        PageInfo<Dict> pageInfo = new PageInfo<>(list);
        pageDataVo.setData(pageInfo.getList());
        pageDataVo.setRecordsTotal(pageInfo.getTotal());
        pageDataVo.setRecordsFiltered(pageInfo.getTotal());
        return pageDataVo;
    }

    /**
     * 状态切换
     *
     * @param dictId
     * @param status
     */
    @PutMapping(value = UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult switchStatus(@RequestParam("dictId") Integer dictId,
                                   @RequestParam("status") Integer status) {
        try {
            dictDao.updateStatusById(dictId, status);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 删除字典
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable("id") Integer id) {
        try {
            dictDao.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    @GetMapping(UrlConstant.FORM)
    public String form(@RequestParam(value = "dictId", required = false) Integer dictId, Model model) {
        String api = "/dict" + UrlConstant.CREATE;
        if (dictId != null) {
            final Dict dict = dictDao.get(Long.valueOf(dictId));
            model.addAttribute("dict", dict);
            api = "/dict/" + dictId + UrlConstant.UPDATE;
        }
        model.addAttribute("api", api);
        model.addAttribute("dictList", dictDao.findList(new Dict("Dict")));
        return "bdc/dict/form";
    }

    /**
     * 字典修改
     *
     * @param id
     * @param dict
     * @return
     */
    @PostMapping("/{id}" + UrlConstant.UPDATE)
    @LogAopAnnotation
    public String modify(@PathVariable("id") Integer id, Dict dict) {
        dictDao.updateById(id, dict);
        return "redirect:/dict/list";
    }

}
