package com.bingo.admin.commons.result;

import java.util.HashMap;

public class R extends HashMap<String,Object>{
    public R() {
        put("code",0);
        put("msg","操作成功");
    }

    public R(String code, String msg) {
        put("code",code);
        put("msg",msg);
    }

    public static R ok(){
        return new R();
    }
    public static R ok(String msg){
        R r=new R();
        r.put("msg",msg);
        return r;
    }
    public static R error(){
        return error("操作失败");
    }
    public static R error(String msg){
        return error("500",msg);
    }
    public static R error(String code,String msg){
        R r=new R();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
