package com.sy.sunder.ex.webmagic.handle;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author: sy
 * @createTime: 2023-04-18 16:57
 * @description:
 */
public class Test implements PageProcessor {


    private Site site = Site.me()
            //配置域名
            .setDomain("blog.didispace.com")
            //每次请求间隔
            .setSleepTime(2000)
            // 设置代理头信息
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
            ;


    @Override
    public void process(Page page) {
        Selectable url = page.getUrl();
        List<String> all = url.all();
        System.out.println(all);
        List<String> links = page.getHtml().links().regex("http://www.baidu.com").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@id='wrapper']/div[@id='head']/div[@id='u']/a/text()").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        //page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new Test())
                // 设置域名，需设置域名后添加的Cookie才生效
                .addUrl("http://www.baidu.com")
                .thread(3)
                .addPipeline(new ConsolePipeline()).run();

    }
}
