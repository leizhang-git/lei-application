package com.yfyy.dto;

import lombok.*;

/**
 * @Author: zhang lei
 * @Date: 2022/3/23 15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByteFile {

    private byte[] byteFile;

    private String fileName;
}
