package izhen_start.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import izhen_start.common.R;
import izhen_start.entity.Category;
import izhen_start.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/***
 * 分类管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //新增分类
    @PostMapping
    public R<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        //构造分页构造器
        Page<Category> pageInfo=new Page<>(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);
        //进行分页查询
        categoryService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    //根据id删除分类
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除分类，id为{}",ids);
        categoryService.removeById(ids);
        //代码完善之后categoryService.remove(ids);
        return R.success("分类信息删除成功");
    }

    //修改分类
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("分类修改成功");
    }

    //根据条件查询分类数据
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        //添加排序条件
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByAsc(Category::getUpdateTime);
        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }

    }

