package com.artyom.esalr234.message.utils;

import com.artyom.esalr234.model.ChangeLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeLogInfo {
    private ChangeLog changeLog;
    private String changeLogInfo;
}