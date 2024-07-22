package com.ruoyi.im.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.im.domain.QqRegister;
import com.ruoyi.im.service.IQqRegisterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * qq二次登录Controller
 * 
 * @author Ghlz
 * @date 2023-12-28
 */
@Api("qq二次登录Controller")
@Controller
@RequestMapping("/system/register")
public class QqRegisterController extends BaseController
{

    @Autowired
    private IQqRegisterService qqRegisterService;



    /**
     * 查询qq二次登录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QqRegister qqRegister)
    {
                LoginUser loginUser = getLoginUser();
        SysUser sysUser = loginUser.getUser();
        Long id = sysUser.getUserId();
        if (!sysUser.isAdmin()){
            qqRegister.setUserId(id);
        }
        startPage();
        List<QqRegister> list = qqRegisterService.selectQqRegisterList(qqRegister);
        return getDataTable(list);
    }

    /**
     * 导出qq二次登录列表
     */
//    @RequiresPermissions("system:register:export")
    @Log(title = "qq二次登录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QqRegister qqRegister)
    {
        List<QqRegister> list = qqRegisterService.selectQqRegisterList(qqRegister);
        ExcelUtil<QqRegister> util = new ExcelUtil<QqRegister>(QqRegister.class);
        return util.exportExcel(list, "qq二次登录数据");
    }


    /**
     * 新增保存qq二次登录
     */
//    @RequiresPermissions("system:register:add")
    @Log(title = "qq二次登录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QqRegister qqRegister)
    {
        return toAjax(qqRegisterService.insertQqRegister(qqRegister));
    }


    /**
     * 修改保存qq二次登录
     */
//    @RequiresPermissions("system:register:edit")
    @Log(title = "qq二次登录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QqRegister qqRegister)
    {
        return toAjax(qqRegisterService.updateQqRegister(qqRegister));
    }

    /**
     * 删除qq二次登录
     */
//    @RequiresPermissions("system:register:remove")
    @Log(title = "qq二次登录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qqRegisterService.deleteQqRegisterByIds(ids));
    }
}
