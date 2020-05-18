package com.easycomp.ehall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.easycomp.ehall.service.DicService;
import com.easycomp.ehall.entity.Dic;
import com.easycomp.dto.R;
import com.easycomp.enums.Error;
import com.easycomp.ehall.controller.BaseController;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author rensq
 * @create 2020-05-18
 */
@Api(tags = {"字典表"})
@RestController
@RequestMapping("/dic")
public class DicController extends BaseController{

    @Resource
    private DicService dicService;


    @ApiOperation(value = "新增字典表")
    @PostMapping("/add")
    public R add(@RequestBody Dic dic){
        dicService.save(dic);
        return success();
    }

    @ApiOperation(value = "删除字典表")
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") String id){
        dicService.removeById(id);
        return success();
    }

    @ApiOperation(value = "更新字典表")
    @PostMapping("/update")
    public R update(@RequestBody Dic dic){
        dicService.updateById(dic);
        return success();
    }

    @ApiOperation(value = "查询字典表")
    @GetMapping("/listByPage")
    public R findListByPage(@RequestParam @ApiParam(name="pageIndex",value="页码",required=true,defaultValue="1") Integer pageIndex,
                            @RequestParam @ApiParam(name="pageSize",value="每页记录个数",required=true,defaultValue="10") Integer pageSize){

        IPage<Dic> iPage = dicService.page(
                new Page(pageIndex, pageSize)
        );
        return ok(iPage,"查询成功");
    }

    @ApiOperation(value = "id查询字典表")
    @GetMapping("/getById/{id}")
    public R findById(@PathVariable String id){
        return ok(dicService.getById(id));
    }

}
