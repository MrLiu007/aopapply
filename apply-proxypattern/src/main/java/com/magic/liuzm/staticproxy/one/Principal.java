package com.magic.liuzm.staticproxy.one;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/18 23:45
 * @description 校长对象
 */
@Log4j2
public class Principal implements Right {

    /**
     * 采用静态代理模式；
     *
     * 或许你会说不对呀，你这里的Director delete方法和User delete 一样，不已经是代理模式了吗？
     *
     * 其实也不对，因为我们只是将2者方法名设置为一样了，若不一样，从语义上就失去意义。
     */


    private Director director;

    public Principal(Director director) {
        super();
        this.director = director;
    }

    @Override
    public void delete(Integer id) {
        log.info("联系教导主任，了解相关学生与老师情况，学生id="+id);
        director.delete(id);
    }
}
