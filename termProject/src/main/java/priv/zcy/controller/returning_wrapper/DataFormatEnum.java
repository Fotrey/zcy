package priv.zcy.controller.returning_wrapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import priv.zcy.pojo.data_mapping.Ums_member;
import priv.zcy.pojo.data_wrapper_utils.Format;
import priv.zcy.pojo.data_wrapper_utils.MemberInfoFormat;
import priv.zcy.pojo.data_wrapper_utils.WrapperDataFormat;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DataFormatEnum implements WrapperProcess{
    CHANNEL("成功"),
    LIST("成功"),
    STAR("成功"),
    CONTENT("成功"),
    INTEGER("成功"){
        @Override
        public Object wrapData(Format format, Object body) {
            super.wrapData(format, body);
            WrapperDataFormat wrapperDataFormat= (WrapperDataFormat) format;
            wrapperDataFormat.setData(null);
            return wrapperDataFormat;
        }
    },
    TOKENFORMAT("登陆成功"),
    UMS_ADMIN("获取用户信息"),
    UMS_MEMBER("成功"){
        @Override
        public Object wrapData(Format format, Object body) {
            MemberInfoFormat memberInfoFormat= (MemberInfoFormat) format;
            Ums_member member = (Ums_member) body;
            memberInfoFormat.getData().put("id",member.getId());
            memberInfoFormat.getData().put("attributes",member);
            return memberInfoFormat;
        }
    };



    private String message;

    @Override
    public Object wrapData(Format format, Object body) {
        WrapperDataFormat wrapperDataFormat= (WrapperDataFormat) format;
        wrapperDataFormat.setCode(20000);
        wrapperDataFormat.setData(body);
        wrapperDataFormat.setMessage(this.getMessage());
        return wrapperDataFormat;
    }
}
