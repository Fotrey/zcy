package priv.zcy.controller.returning_wrapper;

import priv.zcy.pojo.data_wrapper_utils.Format;

public interface WrapperProcess {
    Object wrapData(Format format,Object body);
}
