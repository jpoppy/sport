package com.poppy.sport;

import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Ok("json:full")
@Fail("http:500")
@Localization(value = "msg/", defaultLocalizationKey = "zh-CN")
@Views({ BeetlViewMaker.class })
@Modules(scanPackage = true)
@SetupBy(value = MainSetup.class)
@IocBy(type = ComboIocProvider.class, args = { "*js", "ioc/", "*anno", "com.poppy.sport", "*tx", "*org.nutz.integration.quartz.QuartzIocLoader" })
public class MainModule {

}
