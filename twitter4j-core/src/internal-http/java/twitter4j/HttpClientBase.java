package twitter4j;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import twitter4j.auth.Authorization;

public abstract class HttpClientBase implements HttpClient, Serializable {
    private static final Logger logger = Logger.getLogger(HttpClientBase.class);
    private static final long serialVersionUID = -8016974810651763053L;

    protected final HttpClientConfiguration CONF;

    private final Map<String, String> requestHeaders;
    private final SSLSocketFactory sslSocketFactory;

    public HttpClientBase(HttpClientConfiguration conf) {
        this.CONF = conf;

        this.requestHeaders = new HashMap<>();
        this.requestHeaders.put("X-Twitter-Client-Version", Version.getVersion());
        this.requestHeaders.put("X-Twitter-Client-URL", "http://twitter4j.org/en/twitter4j-" + Version.getVersion() + ".xml");
        this.requestHeaders.put("X-Twitter-Client", "Twitter4J");
        this.requestHeaders.put("User-Agent", "twitter4j http://twitter4j.org/ /" + Version.getVersion());
        if (conf.isGZIPEnabled()) {
            this.requestHeaders.put("Accept-Encoding", "gzip");
        }

        // https://stackoverflow.com/questions/30121510/java-httpsurlconnection-and-tls-1-2
        // If the KeyManager[] parameter is null, the installed security providers are searched for the highest-priority implementation of the KeyManagerFactory, from which an appropriate KeyManager is obtained.
        // If the TrustManager[] parameter is null, the installed security providers are searched for the highest-priority implementation of the TrustManagerFactory, from which an appropriate TrustManager is obtained.
        // Likewise, the SecureRandom parameter can be null, in which case a default implementation is used.
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, new java.security.SecureRandom());
            this.sslSocketFactory = sslContext.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Generating TLSv1.2 SSLContext failed", e);
        }

    }

    protected SSLSocketFactory getTlsSslSocketFactory() {
        return sslSocketFactory;
    }

    protected boolean isProxyConfigured() {
        return CONF.getHttpProxyHost() != null && !CONF.getHttpProxyHost().equals("");
    }

    public void write(DataOutputStream out, String outStr) throws IOException {
        out.writeBytes(outStr);
        logger.debug(outStr);
    }

    @Override
    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    @Override
    public void addDefaultRequestHeader(String name, String value) {
        requestHeaders.put(name, value);
    }

    @Override
    public final HttpResponse request(HttpRequest req) throws TwitterException {
        return handleRequest(req);
    }

    @Override
    public final HttpResponse request(HttpRequest req, HttpResponseListener listener) throws TwitterException {
        try {
            HttpResponse res = handleRequest(req);
            if (listener != null) {
                listener.httpResponseReceived(new HttpResponseEvent(req, res, null));
            }
            return res;
        } catch (TwitterException te) {
            if (listener != null) {
                listener.httpResponseReceived(new HttpResponseEvent(req, null, te));
            }
            throw te;
        }
    }

    abstract HttpResponse handleRequest(HttpRequest req) throws TwitterException;

    @Override
    public HttpResponse get(String url, HttpParameter[] parameters
            , Authorization authorization, HttpResponseListener listener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, url, parameters, authorization, this.requestHeaders), listener);
    }

    @Override
    public HttpResponse get(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.GET, url, null, null, this.requestHeaders));
    }

    @Override
    public HttpResponse post(String url, HttpParameter[] parameters
            , Authorization authorization, HttpResponseListener listener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, url, parameters, authorization, this.requestHeaders), listener);
    }

    @Override
    public HttpResponse post(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.POST, url, null, null, this.requestHeaders));
    }

    @Override
    public HttpResponse delete(String url, HttpParameter[] parameters
            , Authorization authorization, HttpResponseListener listener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.DELETE, url, parameters, authorization, this.requestHeaders), listener);
    }

    @Override
    public HttpResponse delete(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.DELETE, url, null, null, this.requestHeaders));
    }

    @Override
    public HttpResponse head(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.HEAD, url, null, null, this.requestHeaders));
    }

    @Override
    public HttpResponse put(String url, HttpParameter[] parameters
            , Authorization authorization, HttpResponseListener listener) throws TwitterException {
        return request(new HttpRequest(RequestMethod.PUT, url, parameters, authorization, this.requestHeaders), listener);
    }

    @Override
    public HttpResponse put(String url) throws TwitterException {
        return request(new HttpRequest(RequestMethod.PUT, url, null, null, this.requestHeaders));
    }


}
