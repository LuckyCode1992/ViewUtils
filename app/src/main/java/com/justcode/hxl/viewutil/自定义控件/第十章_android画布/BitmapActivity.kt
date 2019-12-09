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
        val bmp1 = BitmapFactory.decodeResource(resources, R.drawable.dog)
        val bmpDrawable = BitmapDrawable(resources, bmp1)
        iv_1.setImageDrawable(bmpDrawable)

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
        val b1 = BitmapFactory.decodeResource(resources, R.drawable.dog)
        iv_2.setImageBitmap(b1)

        //decodeFile()  文件的路径
//        val b2 = BitmapFactory.decodeFile("/data/data/xxx.png")

        //decodeByteArray()
        // 1.开启异步线程去获取网络图片
        // 2.网络返回InputStream
        // 3.InputStream转换为byte[]
        // 4.解析

        Thread {
            try {
                val data = getImage("http://wx2.sinaimg.cn/mw600/0076BSS5ly1g9ibf2nxeuj31900u0tty.jpg")
                val length = data?.size
                val b3 = BitmapFactory.decodeByteArray(data, 0, length ?: 0)
                iv_3.post {
                    iv_3.setImageBitmap(b3)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

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
        op2.inSampleSize = calSampleSize(op1, 500, 500)
        val bmp = BitmapFactory.decodeResource(resources, R.drawable.scenery, op2)
        iv_4.setImageBitmap(bmp)

        /**
         * 创建bitmap的方法2：静态方法
         */
        // createBitmap(width,height,config)

        //createBitmap(source,x,y,width,height)  : 裁剪图像
        // source:裁剪的源图像
        // x，y ：裁剪的的起始点
        // width,height：裁剪的宽高
        val src = BitmapFactory.decodeResource(resources, R.drawable.dog)
        val cutedBmp = Bitmap.createBitmap(src, src.width / 3, src.height / 3, src.width / 3, src.height / 3)

        iv_5.setImageBitmap(cutedBmp)

        //createBitmap(source,x,y,width,height,matrix,filter) ：裁剪并添加矩阵
        val matrix = Matrix()
        matrix.setScale(2f, 1f)
        val src2 = BitmapFactory.decodeResource(resources, R.drawable.dog)
        val cutedBmp2 =
            Bitmap.createBitmap(src2, src2.width / 3, src2.height / 3, src2.width / 3, src2.height / 3, matrix, true)
        iv_6.setImageBitmap(cutedBmp2)

        //createScaledBitmap(src,dstWidth,dstHeight,filter):缩放
        // dstWidth:缩放后的宽度
        val src3 = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        val bitmap3 = Bitmap.createScaledBitmap(src3, 300, 200, true)
        iv_7.setImageBitmap(bitmap3)


        // setPixel()和getPixel() 针对像素进行设置和获取
        val src4 = BitmapFactory.decodeResource(resources, R.drawable.dog)
        iv_8.setImageBitmap(src4)
        val dseBmp = src4.copy(Bitmap.Config.ARGB_8888, true)
        for (h in 0 until src4.height) {
            for (w in 0 until src4.width) {
                val originColor = src4.getPixel(w, h)
                var red = Color.red(originColor)
                val alpha = Color.alpha(originColor)
                var green = Color.green(originColor)
                var blue = Color.blue(originColor)
                if (green < 200) {
                    green += 30
                }
                dseBmp.setPixel(w, h, Color.argb(alpha, red, green, blue))
            }
        }
        iv_9.setImageBitmap(dseBmp)


        val bitmap10 = BitmapFactory.decodeResource(resources,R.drawable.dog)
        val watermark = BitmapFactory.decodeResource(resources,R.drawable.watermark)
        val result = createWaterBitmap(bitmap10,watermark)
        iv_10.setImageBitmap(result)
    }
}

fun createWaterBitmap(src: Bitmap?, watermark: Bitmap?): Bitmap? {
    if (src == null || watermark == null)
        return null

    val w = src.width
    val h = src.height
    val ww = watermark.width
    val hh = watermark.height

    //创建空白图像
    //创建一个新的和src长度，宽度一样的Bitmap
    val newBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
    val cv = Canvas(newBitmap)
    //画原图
    cv.drawBitmap(src,0f,0f,null)
    //在src 右下角画水印
    cv.drawBitmap(watermark,(w-ww+5).toFloat(),(h-hh+5).toFloat(),null)
    return newBitmap
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

class LinearGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var dstBitmap: Bitmap
    val paint = Paint()

    init {
        val width0 = 500
        val height0 = 300
        dstBitmap = Bitmap.createBitmap(width0, height0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(dstBitmap)
        val paint0 = Paint()
        val linearGradient = LinearGradient(
            width0 / 2f,
            0f,
            width0 / 2f,
            height0.toFloat(),
            0xffffffff.toInt(),
            0xff000000.toInt(),
            Shader.TileMode.CLAMP
        )
        paint0.shader = linearGradient
        canvas.drawRect(0f, 0f, width0.toFloat(), height0.toFloat(), paint0)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(dstBitmap, 0f, 0f, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawRect(0f, 0f, dstBitmap.width.toFloat(), dstBitmap.height.toFloat(), paint)
    }
}

