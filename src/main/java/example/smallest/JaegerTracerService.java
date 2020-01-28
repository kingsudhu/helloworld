package example.smallest;


import io.jaegertracing.Configuration;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component

public class JaegerTracerService {

    private Tracer tracer;

    @Bean
    public Tracer jaegerTracer() {
        val samplerConfig = Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        val reporterConfig = Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        val config = new Configuration("Welcome Hello World").withSampler(samplerConfig).withReporter(reporterConfig);
        tracer = config.getTracer();
        return tracer;
    }

    public Span span(String s) {
        return tracer.buildSpan(s).start();
    }

    public Span spanChildOf(String s, Span rootSpan) {
        return tracer.buildSpan(s).asChildOf(rootSpan).start();
    }

    public void restFinish(Span span) {
        restFinish(span, 200);
    }

    public void restFinish(Span span, Number num) {
        span.setTag("statusCode", num);
        finish(span);
    }

    public void restExceptionFinish(Span span, Number num, Exception ex) {
        restFinish(span, num);
    }

    public void finish(Span span) {
        span.finish();
    }

    public void exceptionFinish(Span span, Exception ex) {
        finish(span);
    }
}
