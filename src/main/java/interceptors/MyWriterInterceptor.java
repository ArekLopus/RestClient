package interceptors;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

//Used in custom.entity.format classes
public class MyWriterInterceptor implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println("Clients MyWriterInterceptor.aroundWriteTo() called");
        context.proceed();
    }
    
}
