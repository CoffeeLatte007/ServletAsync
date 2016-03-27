package listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lizhaoz on 2016/3/27.
 */

public class TeacherListener implements AsyncListener {
    final SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("在"+formatter.format(new Date())+"工作处理完成");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("在"+formatter.format(new Date())+"工作超时");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("在"+formatter.format(new Date())+"工作处理错误");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("在"+formatter.format(new Date())+"工作处理开始");
    }
}
