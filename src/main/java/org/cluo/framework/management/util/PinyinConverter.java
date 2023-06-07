package org.cluo.framework.management.util;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;

import java.text.Collator;
import java.util.Locale;

public class PinyinConverter {
    public static String convertToPinyin(String chineseText) {
        return PinyinHelper.toPinyin(chineseText, PinyinStyleEnum.FIRST_LETTER, "");
    }
}
