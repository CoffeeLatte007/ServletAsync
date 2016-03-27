package servlet;

import listener.TeacherListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by lizhaoz on 2016/3/27.
 */
@WebServlet(
        name = "WorkServlet",
        urlPatterns   = "/work",
        asyncSupported = true
)
public class WorkServlet extends HttpServlet {
    private static  final long serialVersionUID =1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置ContentType,关闭缓存
        resp.setContentType("text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control","private");
        resp.setHeader("Pragma","no-cache");
        final PrintWriter writer= resp.getWriter();
        writer.println("老师检查作业了");
        writer.flush();
        List<String> zuoyes=new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            zuoyes.add("zuoye"+i);;
        }
        final AsyncContext ac=req.startAsync();//开启异步请求
        ac.addListener(new TeacherListener());
        doZuoye(ac, zuoyes);
        writer.println("老师布置作业");
        writer.flush();
    }

    private void doZuoye(final AsyncContext ac, final List<String> zuoyes) {
        ac.setTimeout(1*60*60*1000L);
        ac.start(new Runnable() {
            @Override
            public void run() {
                //通过response获得字符输出流
                try {
                    PrintWriter writer=ac.getResponse().getWriter();
                    for (String zuoye:zuoyes) {
                        writer.println("\""+zuoye+"\"请求处理中");
                        Thread.sleep(1*1000L);
                        writer.flush();
                    }
                    ac.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
