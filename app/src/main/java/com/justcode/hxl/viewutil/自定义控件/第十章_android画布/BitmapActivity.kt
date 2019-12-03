package com.justcode.hxl.viewutil.自定义控件.第十章_android画布

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.justcode.hxl.viewutil.R
import kotlinx.android.synthetic.main.activity_bitmap.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class BitmapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap)
        /**
         *  Bitmap 在绘图中的使用
         *  1.转换为BitmapDrawable对象使用
         *  2.当作画布使用
         *  2.1 使用默认画布
         *  2.2 自建画布
         */
//        val bmp = BitmapFactory.decodeResource(resources, R.drawable.dog)
//        val bmpDrawable = BitmapDrawable(resources, bmp)
//        iv_1.setImageDrawable(bmpDrawable)

        /**
         * bitmap 格式
         *
         *  A ：透明度，R：红色，G：绿色，B：蓝色
         *  - ALPHA_8:表示8位Alpha位图，只存储Alpha位，不存颜色，占用1字节，只有透明度
         *  - ARGB_4444:表示16位（4+4+4+4）ARGB位图，占用2字节
         *  - ARGB_8888:表示32位（8+8+8+8）ARGB位图，占用4字节
         *  - RGB_565:表示16（5+6+5）位RGB位图
         *  一般我们使用 ARGB_8888 存储ARGB_8888,如果没有透明度，可以考虑使用RGB_565
         */

        /**
         * bitmap 压缩格式
         * Bitmap.CompressFormat:
         * - JPEG
         * - PNG
         * - WEBP
         */

        /**
         * 创建 Bitmap的方法：
         * BitmapFactory的一些列方法
         */

        //decodeResource()
//        val b1 = BitmapFactory.decodeResource(resources, R.drawable.dog)
//        iv_1.setImageBitmap(b1)

        //decodeFile()  文件的路径
//        val b2 = BitmapFactory.decodeFile("/data/data/xxx.png")

        //decodeByteArray()
        // 1.开启异步线程去获取网络图片
        // 2.网络返回InputStream
        // 3.InputStream转换为byte[]
        // 4.解析

//        Thread {
//            try {
//                val data = getImage("http://wx2.sinaimg.cn/mw600/0076BSS5ly1g9ibf2nxeuj31900u0tty.jpg")
//                val length = data?.size
//                val b3 = BitmapFactory.decodeByteArray(data, 0, length ?: 0)
//                iv_1.post {
//                    iv_1.setImageBitmap(b3)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }.start()

        /**
         *  BitmapFactory.Options
         *
         *   改变图片的宽度，高度，缩放比例，减少图片像素大小
         */

        // inJustDecodeBounds 获取图片信息
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        val b4 = BitmapFactory.decodeResource(resources, R.drawable.avator, option)
        Log.d("option_", "bitmap:${b4}")
        Log.d("option_", "width:${option.outWidth}-height:${option.outHeight}-mimeType:${option.outMimeType}")

        //inSampleSize 压缩图片 采样率（每隔多少像素，采集一个像素返回，其他丢弃）
        // 2的幂数，1，2，4，8，但是不能取1

        val op1 = BitmapFactory.Options()
        op1.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.scenery, op1)
        //现在原始宽高存储Option对象的宽高实例域中

        val op2 = BitmapFactory.Options()
        op2.inSampleSize = calSampleSize(op1,500,500)
        val bmp = BitmapFactory.decodeResource(resources,R.drawable.scenery,op2)
        iv_1.setImageBitmap(bmp)

    }
}

fun calSampleSize(option: BitmapFactory.Options, dstWidth: Int, dstHeight: Int): Int {
    val rawWidth = option.outWidth
    val rawHeight = option.outHeight
    var inSampleSize = 1
    if (rawWidth > dstWidth || rawHeight > dstHeight) {
        val rationWidth = rawWidth / dstWidth
        val rationHegth = rawHeight / dstHeight
        inSampleSize = Math.min(rationWidth, rationHegth)
    }
    return inSampleSize
}

fun getImage(path: String): ByteArray? {
    val url = URL(path)
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"
    httpURLConnection.readTimeout = 6 * 1000
    var inputStream: InputStream
    if (httpURLConnection.responseCode == 200) {
        inputStream = httpURLConnection.inputStream
        val result = readStream(inputStream)
        inputStream.close()
        return result
    }
    return null
}

@Throws(IOException::class)
fun readStream(inputStream: InputStream): ByteArray {
    val outputStream = ByteArrayOutputStream()
    val buffer = ByteArray(1024)
    var len = -1
    len = inputStream.read(buffer)
    while ((len) != -1) {
        outputStream.write(buffer, 0, len)
        len = inputStream.read(buffer)
    }
    outputStream.close()
    inputStream.close()
    return outputStream.toByteArray()
}

class ViewDemo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//使用默认画布
//canvas 中默认保存有一个Bitmap对象，使用canvas绘图时，最终都是画在这个Bitmap上的，这个Bitmap就是默认的画布
        val rect = RectF(120f, 10f, 210f, 100f)
        canvas.drawRect(rect, paint)

//自建画布
//创建一个空白的Bitmap，然后创建一个Canvas对象，然后调用canvas的绘图函数，画在这个bitmap上，最后，将这个bitmap保存到本地，也可以画到View上
        val bitmap = Bitmap.createBitmap(200, 100, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.YELLOW)
    }
}