package org.cluo.framework.management.exception;

/**
 * @author canfuu.cts
 * @class LeftSubDuplicatedException
 * @date 2023/6/4 14:06
 */
public class LeftSubDuplicatedException extends RuntimeException{

    public LeftSubDuplicatedException() {
        super("左边子栏目重复");
    }

}
