package com.nwn.crafts.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppInfo {

    private String name;

    private String version;

    private String env;
}
