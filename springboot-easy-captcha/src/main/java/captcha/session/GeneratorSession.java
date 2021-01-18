package captcha.session;

import org.apache.catalina.util.SessionIdGeneratorBase;

/**
 * @author bz
 * @date 2020/9/15
 */
public class GeneratorSession extends SessionIdGeneratorBase {

    @Override
    public String generateSessionId(String route) {
        byte[] random = new byte[16];
        int sessionIdLength = this.getSessionIdLength();
        StringBuilder buffer = new StringBuilder(2 * sessionIdLength + 20);
        int resultLenBytes = 0;

        while(resultLenBytes < sessionIdLength) {
            this.getRandomBytes(random);

            for(int j = 0; j < random.length && resultLenBytes < sessionIdLength; ++j) {
                byte b1 = (byte)((random[j] & 240) >> 4);
                byte b2 = (byte)(random[j] & 15);
                if (b1 < 10) {
                    buffer.append((char)(48 + b1));
                } else {
                    buffer.append((char)(65 + (b1 - 10)));
                }

                if (b2 < 10) {
                    buffer.append((char)(48 + b2));
                } else {
                    buffer.append((char)(65 + (b2 - 10)));
                }

                ++resultLenBytes;
            }
        }

        if (route != null && route.length() > 0) {
            buffer.append('.').append(route);
        } else {
            String jvmRoute = this.getJvmRoute();
            if (jvmRoute != null && jvmRoute.length() > 0) {
                buffer.append('.').append(jvmRoute);
            }
        }

        return buffer.toString();
    }

    public static void main(String[] args) {
        GeneratorSession generatorSession = new GeneratorSession();
        String sessionId = generatorSession.generateSessionId(null);
        System.out.println(sessionId);
    }
}
