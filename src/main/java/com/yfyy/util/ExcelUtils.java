package com.yfyy.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhang lei
 * @Date: 2022/4/11 13:57
 */
public class ExcelUtils {

    /**
     * 读取Excel文件
     * @param path
     * @return
     */
    public static List<Map<String, Object>> readExcel(String path) {
        ExcelReader reader = ExcelUtil.getReader(path);
        return reader.readAll();
    }
}
