package interceptors;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

//Used in custom.entity.format classes
public class MyReaderInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println("Clients MyReaderInterceptor.aroundReadFrom() called");
        return context.proceed(); 
    }

}
