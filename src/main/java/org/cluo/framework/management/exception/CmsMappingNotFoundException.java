package org.cluo.framework.management.exception;

/**
 * @author canfuu.cts
 * @class CmsMappingNotFoundException
 * @date 2023/6/4 16:19
 */
public class CmsMappingNotFoundException extends RuntimeException {
    public CmsMappingNotFoundException() {
        super("CmsMapping没有找到");
    }
}
