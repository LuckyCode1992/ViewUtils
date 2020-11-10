package com.justcode.hxl.viewutil.自定义View.绘制1_1.饼图.utils

import android.graphics.PointF


object CircleUtil {

    /**
     * 根据角度angle获取在此角与圆心连线中距离圆心为distanceToCenter远的点
     *
     * @param angle            以圆心为起点水平向右作边a,与此边a按顺时针方向的夹角
     * @param distanceToCenter 距离圆心的距离
     * @param cX               圆心
     * @param cY               圆心
     */
    fun getPositionByAngle(angle: Float, distanceToCenter: Int, cX: Int, cY: Int): PointF {
        val res = PointF()
        val x1: Double
        val y1: Double
        //角度转成弧度
        //角度转成弧度
        val radians: Double = angle2radians(angle)
        x1 = cX + distanceToCenter * Math.cos(radians)
        y1 = cY + distanceToCenter * Math.sin(radians)
        res[x1.toFloat()] = y1.toFloat()
        return res
    }

    /**
     * 根据角度angle获取在此角与圆心连线中距离圆心为distanceToCenter远的点
     *
     * @param sAngle           以圆心为起点水平向右作边a,与此边a按顺时针方向的夹角
     * @param sweepAngle       扫过的角度
     * @param distanceToCenter 距离圆心的距离
     * @param cX               圆心
     * @param cY               圆心
     * @return
     */
    fun getPositionByAngle(
        sAngle: Float,
        sweepAngle: Float,
        distanceToCenter: Int,
        cX: Int,
        cY: Int
    ): PointF {
        val angle = sAngle + sweepAngle / 2
        return getPositionByAngle(angle, distanceToCenter, cX, cY)
    }

    /**
     * 根据坐标计算点相对圆心的角度,与从圆心水平向右作的边的夹角
     *
     * @param x1 坐标
     * @param y1 坐标
     * @param cX 圆心
     * @param cY 圆心
     * @return 角度, 若radius不为0且坐标超出圆的范围时返回-1.
     */
    fun getAngleByPosition(
        x1: Float,
        y1: Float,
        cX: Int,
        cY: Int
    ): Float {
        return getAngleByPosition(x1, y1, cX, cY, 0)
    }

    /**
     * 根据坐标计算点相对圆心的角度,与从圆心水平向右作的边的夹角
     *
     * @param x1     坐标
     * @param y1     坐标
     * @param cX     圆心
     * @param cY     圆心
     * @param radius 圆的半径,用于判断点是否超出圆的范围,超出时返回-1.传值为0时不判断是否超出范围
     * @return 角度, 若radius不为0且坐标超出圆的范围时返回-1.
     */
    fun getAngleByPosition(
        x1: Float,
        y1: Float,
        cX: Int,
        cY: Int,
        radius: Int
    ): Float { // 对边
        val oppositeSide = y1 - cY
        // 邻边
        val adjacentSide = x1 - cX
        if (radius > 0) { //半径大于0时,判断坐标点是否超出圆形范围
// 点超出圆形范围
            if (Math.abs(oppositeSide) > radius || Math.abs(adjacentSide) > radius) {
                return -1f
            }
            // 点到圆心距离超过了半径
            if (Math.sqrt(oppositeSide * oppositeSide + adjacentSide * adjacentSide.toDouble()) > radius) {
                return -1f
            }
        }
        //弧度
        val tanRadians = Math.atan(oppositeSide / adjacentSide.toDouble())
        var angle = radians2angle(tanRadians)
        if (x1 < cX) {
            angle = 180 + angle
        } else if (x1 > cX && y1 < cY) {
            angle = 360 + angle
        }
        return angle.toFloat() % 360
    }

    /**
     * 获取扇形中心点坐标
     *
     * @param sAngle     扇形起始角度
     * @param sweepAngle 扇形总度数
     * @param cX         圆心
     * @param cY         圆心
     * @param radius     半径
     * @return 扇形中心点坐标
     */
    fun getSectorCenterPosition(
        sAngle: Float,
        sweepAngle: Float,
        cX: Int,
        cY: Int,
        radius: Int
    ): PointF {
        val angle = sAngle + sweepAngle / 2
        return getPositionByAngle(angle, radius / 2, cX, cY)
    }

    /**
     * 获取触摸点到圆心的直线距离
     *
     * @param cx     圆心
     * @param cy     圆心
     * @param touchX 触摸位置
     * @param touchY 触摸位置
     * @return 圆心到触摸点的距离(正数)
     */
    fun getDistanceByPosition(
        cx: Int,
        cy: Int,
        touchX: Float,
        touchY: Float
    ): Float {
        val side1 = Math.abs(cx.toFloat() - touchX)
        val side2 = Math.abs(cy.toFloat() - touchY)
        return Math.sqrt(side1 * side1 + side2 * side2.toDouble()).toFloat()
    }

    /**
     * targetAngle是否在sectorStartAngle+sectorEndAngle描述的扇形区域内
     *
     * @param targetAngle
     * @param sectorStartAngle
     * @param sectorEndAngle
     * @return
     */
    fun isContainAngle(
        targetAngle: Float,
        sectorStartAngle: Float,
        sectorEndAngle: Float
    ): Boolean {
        return (targetAngle.compareTo(sectorStartAngle) == 0 ||
                targetAngle.compareTo(sectorStartAngle) == 1) &&
                targetAngle.compareTo(sectorEndAngle) == -1
    }


    /**
     * 角度转弧度
     *
     * @param angle
     * @return
     */
    fun angle2radians(angle: Float): Double {
        return angle / 180f * Math.PI
    }

    /**
     * 弧度转角度
     *
     * @param radians
     * @return
     */
    fun radians2angle(radians: Double): Double {
        return 180f * radians / Math.PI
    }
}
