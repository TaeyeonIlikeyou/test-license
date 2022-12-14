package com.abandon.license;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义需要校验的License参数
 * @author guozx
 * @date 2022/12/13
 */
@Data
public class LicenseCheckModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可被允许的IP地址（白名单）
     */
    private List<String> ipAddress;

    /**
     * 可被允许的MAC地址
     */
    private List<String> macAddress;

    /**
     * 可被允许的CPU序列号
     */
    private String cpuSerial;

    /**
     * 可被允许的主板序列号
     */
    private String mainBoardSerial;
}
