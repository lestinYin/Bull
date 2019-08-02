package com.lestin.yin.api.utils

import com.lestin.yin.MyApplication
import com.lestin.yin.utils.LogUtil
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


/**
 *
 * @ProjectName:    Taurus
 * @Package:        com.lestin.yin.api.utils
 * @ClassName:      SslUtils
 * @Description:     验证证书
 * @Author:         lestin.yin
 * @CreateDate:     2019-08-02 18:35
 * @Version:        1.0
 */
class SslUtils {
    companion object {

    private fun getKeyStore(fileName: String): KeyStore? {
        var keyStore: KeyStore? = null
        try {

            val assetManager = MyApplication.context.assets
            val cf = CertificateFactory.getInstance("X.509")
            val caInput = assetManager.open(fileName)
            val ca: Certificate
            try {
                ca = cf.generateCertificate(caInput)
            } finally {
                caInput.close()
            }

            val keyStoreType = KeyStore.getDefaultType()
            keyStore = KeyStore.getInstance(keyStoreType)
            keyStore!!.load(null, null)
            keyStore!!.setCertificateEntry("ca", ca)
        } catch (e: Exception) {
        }

        return keyStore
    }

        fun getSslContextForCertificateFile(fileName: String): SSLContext {
            try {
                val keyStore = getKeyStore(fileName)
                val sslContext = SSLContext.getInstance("SSL")
                val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                sslContext.init(null, trustManagerFactory.getTrustManagers(), SecureRandom())
                return sslContext
            } catch (e: Exception) {
                val msg = "Error during creating SslContext for certificate from assets"
                LogUtil.e("SslUtils", msg, e)
                throw RuntimeException(msg)
            }

        }
    }

}