package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.easycomp.dto.R;
import com.easycomp.enums.Error;
import javax.annotation.Resource;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;

</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @create ${date}
 */
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{
<#else>public class ${table.controllerName} extends BaseController{
</#if>

    @Resource
    private ${table.serviceName} ${(table.serviceName)?uncap_first};


    @ApiOperation(value = "新增${table.comment!}")
    @PostMapping("/add")
    public R add(@RequestBody ${entity} ${entity?uncap_first}){
        ${(table.serviceName)?uncap_first}.save(${entity?uncap_first});
        return success();
    }

    @ApiOperation(value = "删除${table.comment!}")
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") String id){
        ${(table.serviceName)?uncap_first}.removeById(id);
        return success();
    }

    @ApiOperation(value = "更新${table.comment!}")
    @PostMapping("/update")
    public R update(@RequestBody ${entity} ${entity?uncap_first}){
        ${(table.serviceName)?uncap_first}.updateById(${entity?uncap_first});
        return success();
    }

    @ApiOperation(value = "查询${table.comment!}分页数据")
    @GetMapping("/listByPage")
    public R findListByPage(@RequestParam @ApiParam(name="pageIndex",value="页码",required=true,defaultValue="1") Integer pageIndex,
                            @RequestParam @ApiParam(name="pageSize",value="每页记录个数",required=true,defaultValue="10") Integer pageSize){

        IPage<${entity}> iPage = ${(table.serviceName)?uncap_first}.page(
                new Page(pageIndex, pageSize)
        );
        return ok(iPage,"查询成功");
    }

    @ApiOperation(value = "id查询${table.comment!}")
    @GetMapping("/getById/{id}")
    public R findById(@PathVariable String id){
        return ok(${(table.serviceName)?uncap_first}.getById(id));
    }

}
</#if>