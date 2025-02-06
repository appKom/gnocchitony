import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver
import org.springframework.util.StringUtils

class CookieTokenResolver : BearerTokenResolver {

    private val tokenName = "access_token"

    override fun resolve(request: HttpServletRequest): String? {
        val cookies = request.cookies ?: return null
        val tokenCookie = cookies.find { it.name == tokenName }

        return tokenCookie?.value?.takeIf { StringUtils.hasText(it) }
    }

}
