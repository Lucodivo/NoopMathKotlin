package com.inasweaterpoorlyknit

import kotlin.math.sqrt

/*
    TODO:
    -  
 */

data class Vec2(val x: Float, val y: Float) {
    val lenSq get() = (x*x) + (y*y)
    val len get() = sqrt(lenSq)
    val normalized get() = this / len
    fun dot(v: Vec2) = (x * v.x) + (y * v.y)
}

data class Vec3(val x: Float, val y: Float, val z: Float) {
    val lenSq get() = (x*x) + (y*y) + (z*z)
    val len get() = sqrt(lenSq)
    val normalized get() = this / len
    fun dot(v: Vec3) = (x * v.x) + (y * v.y) + (z * v.z)
    companion object {
        fun cross(a: Vec3, b: Vec3) = Vec3(a.y * b.z - b.y * a.z,
                                            a.z * b.x - b.z * a.x,
                                            a.x * b.y - b.x * a.y)
    }
    // Special override to ensure -0.0f is equal to +0.0f
    override fun equals(other: Any?) = other is Vec3 && (x - other.x) == 0.0f && (y - other.y) == 0.0f && (z - other.z) == 0.0f
}

data class Vec4(val x: Float, val y: Float, val z: Float, val w: Float) {
    val lenSq get() = (x*x) + (y*y) + (z*z) + (w*w)
    val len get() = sqrt(lenSq)
    val normalized get() = this / len
    fun dot(v: Vec4) = (x * v.x) + (y * v.y) + (z * v.z) + (w * v.w)
}

class Mat2 {
    val elements = FloatArray(2 * 2)
    val col0 get() = Vec2(elements[(0*2)+0], elements[(1*2)+0])
    val col1 get() = Vec2(elements[(0*2)+1], elements[(1*2)+1])
    val row0 get() = Vec2(elements[(0*2)+0], elements[(0*2)+1])
    val row1 get() = Vec2(elements[(1*2)+0], elements[(1*2)+1])

    constructor() // zeroed out Mat4
    constructor(diagonal: Float) {
        elements[(0*2)+0] = diagonal
        elements[(1*2)+1] = diagonal
    }

    constructor(element00: Float, element01: Float,
                element10: Float, element11: Float,) {
        elements[0] = element00
        elements[1] = element01
        elements[2] = element10
        elements[3] = element11
    }

    override fun equals(other: Any?) = other is Mat2 &&
                                       this.elements[0] == elements[0] &&
                                       this.elements[1] == elements[1] &&
                                       this.elements[2] == elements[2] &&
                                       this.elements[3] == elements[3]

    fun equalsEpsilon(other: Mat3, epsilon: Float = 0.01f): Boolean {
        return this.elements.zip(other.elements).any{
            val diff = it.first - it.second
            diff < epsilon && diff > -epsilon
        }
    }

    override fun hashCode() = elements.contentHashCode()
}

class Mat3 {
    val elements = FloatArray(3 * 3)
    val col0 get() = Vec3(elements[(0*3)+0], elements[(1*3)+0], elements[(2*3)+0])
    val col1 get() = Vec3(elements[(0*3)+1], elements[(1*3)+1], elements[(2*3)+1])
    val col2 get() = Vec3(elements[(0*3)+2], elements[(1*3)+2], elements[(2*3)+2])
    val row0 get() = Vec3(elements[(0*3)+0], elements[(0*3)+1], elements[(0*3)+2])
    val row1 get() = Vec3(elements[(1*3)+0], elements[(1*3)+1], elements[(1*3)+2])
    val row2 get() = Vec3(elements[(2*3)+0], elements[(2*3)+1], elements[(2*3)+2])

    constructor() // zeroed out Mat4
    constructor(diagonal: Float) {
        elements[(0*3)+0] = diagonal
        elements[(1*3)+1] = diagonal
        elements[(2*3)+2] = diagonal
    }

    constructor(element00: Float, element01: Float, element02: Float,
                element10: Float, element11: Float, element12: Float,
                element20: Float, element21: Float, element22: Float) {
        elements[0] = element00
        elements[1] = element01
        elements[2] = element02
        elements[3] = element10
        elements[4] = element11
        elements[5] = element12
        elements[6] = element20
        elements[7] = element21
        elements[8] = element22
    }

    override fun equals(other: Any?) = other is Mat3 &&
                                        this.elements[0] == elements[0] &&
                                        this.elements[1] == elements[1] &&
                                        this.elements[2] == elements[2] &&
                                        this.elements[3] == elements[3] &&
                                        this.elements[4] == elements[4] &&
                                        this.elements[5] == elements[5] &&
                                        this.elements[6] == elements[6] &&
                                        this.elements[7] == elements[7] &&
                                        this.elements[8] == elements[8]

    fun equalsEpsilon(other: Mat3, epsilon: Float = 0.01f): Boolean {
        return this.elements.zip(other.elements).any{
            val diff = it.first - it.second
            diff < epsilon && diff > -epsilon
        }
    }

    override fun hashCode() = elements.contentHashCode()
}

class Mat4 {
    val elements = FloatArray(4 * 4)
    val col0 get() = Vec4(elements[(0*4)+0], elements[(1*4)+0], elements[(2*4)+0], elements[(3*4)+0])
    val col1 get() = Vec4(elements[(0*4)+1], elements[(1*4)+1], elements[(2*4)+1], elements[(3*4)+1])
    val col2 get() = Vec4(elements[(0*4)+2], elements[(1*4)+2], elements[(2*4)+2], elements[(3*4)+2])
    val col3 get() = Vec4(elements[(0*4)+3], elements[(1*4)+3], elements[(2*4)+3], elements[(3*4)+3])
    val row0 get() = Vec4(elements[(0*4)+0], elements[(0*4)+1], elements[(0*4)+2], elements[(0*4)+3])
    val row1 get() = Vec4(elements[(1*4)+0], elements[(1*4)+1], elements[(1*4)+2], elements[(1*4)+3])
    val row2 get() = Vec4(elements[(2*4)+0], elements[(2*4)+1], elements[(2*4)+2], elements[(2*4)+3])
    val row3 get() = Vec4(elements[(3*4)+0], elements[(3*4)+1], elements[(3*4)+2], elements[(3*4)+3])

    constructor() // zeroed out Mat4
    constructor(diagonal: Float) {
        elements[(0*4)+0] = diagonal
        elements[(1*4)+1] = diagonal
        elements[(2*4)+2] = diagonal
        elements[(3*4)+3] = diagonal
    }
    constructor(element00: Float, element01: Float, element02: Float, element03: Float,
                element10: Float, element11: Float, element12: Float, element13: Float,
                element20: Float, element21: Float, element22: Float, element23: Float,
                element30: Float, element31: Float, element32: Float, element33: Float) {
        elements[0] = element00
        elements[1] = element01
        elements[2] = element02
        elements[3] = element03
        elements[4] = element10
        elements[5] = element11
        elements[6] = element12
        elements[7] = element13
        elements[8] = element20
        elements[9] = element21
        elements[10] = element22
        elements[11] = element23
        elements[12] = element30
        elements[13] = element31
        elements[14] = element32
        elements[15] = element33
    }

    override fun equals(other: Any?) = other is Mat4 &&
                                        this.elements[0] == elements[0] &&
                                        this.elements[1] == elements[1] &&
                                        this.elements[2] == elements[2] &&
                                        this.elements[3] == elements[3] &&
                                        this.elements[4] == elements[4] &&
                                        this.elements[5] == elements[5] &&
                                        this.elements[6] == elements[6] &&
                                        this.elements[7] == elements[7] &&
                                        this.elements[8] == elements[8] &&
                                        this.elements[9] == elements[9] &&
                                        this.elements[10] == elements[10] &&
                                        this.elements[11] == elements[11] &&
                                        this.elements[12] == elements[12] &&
                                        this.elements[13] == elements[13] &&
                                        this.elements[14] == elements[14] &&
                                        this.elements[15] == elements[15]

    fun equalsEpsilon(other: Mat4, epsilon: Float = 0.01f): Boolean {
        return this.elements.zip(other.elements).any{
            val diff = it.first - it.second
            diff < epsilon && diff > -epsilon
        }
    }

    override fun hashCode() = elements.contentHashCode()
}

/*****
 * OPERATOR OVERLOADS
 */

/*
    Vec2
 */
operator fun Vec2.unaryMinus() = Vec2(-x, -y)
operator fun Vec2.plus(v: Vec2) = Vec2(x+v.x, y+v.y)
operator fun Vec2.minus(v: Vec2) = Vec2(x-v.x, y-v.y)
operator fun Float.times(v: Vec2) = Vec2(v.x*this, v.y*this)
operator fun Vec2.times(s: Float) = Vec2(x*s, y*s)
operator fun Vec2.div(s: Float) = this * (1.0f/s)

/*
    Vec3
 */
operator fun Vec3.unaryMinus() = Vec3(-x, -y, -z)
operator fun Vec3.plus(v: Vec3) = Vec3(x+v.x, y+v.y, z+v.z)
operator fun Vec3.minus(v: Vec3) = Vec3(x-v.x, y-v.y, z-v.z)
operator fun Float.times(v: Vec3) = Vec3(v.x*this, v.y*this, v.z*this)
operator fun Vec3.times(s: Float) = Vec3(x*s, y*s, z*s)
operator fun Vec3.div(s: Float) = this * (1.0f/s)

/*
    Vec4
 */
operator fun Vec4.unaryMinus() = Vec4(-x, -y, -z, -w)
operator fun Vec4.plus(v: Vec4) = Vec4(x+v.x, y+v.y, z+v.z, w+v.w)
operator fun Vec4.minus(v: Vec4) = Vec4(x-v.x, y-v.y, z-v.z, w-v.w)
operator fun Float.times(v: Vec4) = Vec4(v.x*this, v.y*this, v.z*this, v.w*this)
operator fun Vec4.times(s: Float) = Vec4(x*s, y*s, z*s, w*s)
operator fun Vec4.div(s: Float) = this * (1.0f/s)

/*
    Mat2
 */
operator fun Mat2.times(other: Mat2): Mat2 {
    val result = Mat2();

    for(i in 0..1) {
        for(j in 0..1) {
            // dot product of this ith row with other jth column
            result.elements[(i*2)+j] =
                this.elements[(i*2)+0] * other.elements[(0*2)+j] +
                this.elements[(i*2)+1] * other.elements[(1*2)+j]
        }
    }

    return result
}

// Vec2 as column vector
operator fun Mat2.times(v: Vec2) = Vec2(
    v.x * this.elements[(0*2)+0] +
        v.y * this.elements[(0*2)+1],
    v.x * this.elements[(1*2)+0] +
        v.y * this.elements[(1*2)+1]
)
// Vec2 as row vector
operator fun Vec2.times(m: Mat2) = Vec2(
    this.x * m.elements[(0*2)+0] +
        this.y * m.elements[(1*2)+0],
    this.x * m.elements[(0*2)+1] +
        this.y * m.elements[(1*2)+1]
)

/*
    Mat3
 */
operator fun Mat3.times(other: Mat3): Mat3 {
    val result = Mat3();

    for(i in 0..2) {
        for(j in 0..2) {
            // dot product of this ith row with other jth column
            result.elements[(i*3)+j] =
                this.elements[(i*3)+0] * other.elements[(0*3)+j] +
                this.elements[(i*3)+1] * other.elements[(1*3)+j] +
                this.elements[(i*3)+2] * other.elements[(2*3)+j]
        }
    }

    return result
}

// Vec3 as column vector
operator fun Mat3.times(v: Vec3) = Vec3(
    v.x * this.elements[(0*3)+0] +
        v.y * this.elements[(0*3)+1] +
        v.z * this.elements[(0*3)+2],
    v.x * this.elements[(1*3)+0] +
        v.y * this.elements[(1*3)+1] +
        v.z * this.elements[(1*3)+2],
    v.x * this.elements[(2*3)+0] +
        v.y * this.elements[(2*3)+1] +
        v.z * this.elements[(2*3)+2]
)
// Vec3 as row vector
operator fun Vec3.times(m: Mat3) = Vec3(
    this.x * m.elements[(0*3)+0] +
        this.y * m.elements[(1*3)+0] +
        this.z * m.elements[(2*3)+0],
    this.x * m.elements[(0*3)+1] +
        this.y * m.elements[(1*3)+1] +
        this.z * m.elements[(2*3)+1],
    this.x * m.elements[(0*3)+2] +
        this.y * m.elements[(1*3)+2] +
        this.z * m.elements[(2*3)+2]
)

/*
  Mat4
 */
operator fun Mat4.times(other: Mat4): Mat4 {
    val result = Mat4();

    for(i in 0..3) {
        for(j in 0..3) {
            // dot product of this ith row with other jth column
            result.elements[(i*4)+j] =
                this.elements[(i*4)+0] * other.elements[(0*4)+j] +
                this.elements[(i*4)+1] * other.elements[(1*4)+j] +
                this.elements[(i*4)+2] * other.elements[(2*4)+j] +
                this.elements[(i*4)+3] * other.elements[(3*4)+j]
        }
    }

    return result
}

// Vec4 as column vector
operator fun Mat4.times(v: Vec4) = Vec4(
    v.x * this.elements[(0*4)+0] +
        v.y * this.elements[(0*4)+1] +
        v.z * this.elements[(0*4)+2] +
        v.w * this.elements[(0*4)+3],
    v.x * this.elements[(1*4)+0] +
        v.y * this.elements[(1*4)+1] +
        v.z * this.elements[(1*4)+2] +
        v.w * this.elements[(1*4)+3],
    v.x * this.elements[(2*4)+0] +
        v.y * this.elements[(2*4)+1] +
        v.z * this.elements[(2*4)+2] +
        v.w * this.elements[(2*4)+3],
    v.x * this.elements[(3*4)+0] +
        v.y * this.elements[(3*4)+1] +
        v.z * this.elements[(3*4)+2] +
        v.w * this.elements[(3*4)+3]
)
 // Vec4 as row vector
operator fun Vec4.times(m: Mat4) = Vec4(
    this.x * m.elements[(0*4)+0] +
        this.y * m.elements[(1*4)+0] +
        this.z * m.elements[(2*4)+0] +
        this.w * m.elements[(3*4)+0],
    this.x * m.elements[(0*4)+1] +
        this.y * m.elements[(1*4)+1] +
        this.z * m.elements[(2*4)+1] +
        this.w * m.elements[(3*4)+1],
    this.x * m.elements[(0*4)+2] +
        this.y * m.elements[(1*4)+2] +
        this.z * m.elements[(2*4)+2] +
        this.w * m.elements[(3*4)+2],
    this.x * m.elements[(0*4)+3] +
        this.y * m.elements[(1*4)+3] +
        this.z * m.elements[(2*4)+3] +
        this.w * m.elements[(3*4)+3]
)