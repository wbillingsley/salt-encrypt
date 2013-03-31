package com.wbillingsley.encrypt
  
 /**
 * Our own quick-and-dirty Base64 encoder.
 */
object Base64 {

  val sixtyFour = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789=_"  
  
  def unsigned(b:Byte) = (255 & b)             
  
  def encode(b: Array[Byte]) = {
    if (b.length % 3 != 0) throw new IllegalArgumentException("byte array must be of length divisible by 3")

    val buf = new StringBuffer
    val grouped = b.grouped(3)
    for (group <- grouped) {
      var twentyFourBit = 0
      for ((byte, i) <- group.zipWithIndex) {
        twentyFourBit += (unsigned(byte) << (8 * (2-i)))
      }
      for (i <- Seq(0,1,2,3)) {
        val sextet = (twentyFourBit >> (6 * (3-i))) & 63
        buf.append(sixtyFour(sextet))
      }
    }
    buf.toString
  }

  def decode(s: String) = {
    if (s.length % 4 != 0) throw new IllegalArgumentException("string must be of length divisible by 4")

    val arr = Array.ofDim[Byte]((s.length * 3) / 4)

    val grouped = s.grouped(4)
    for ((group, j) <- grouped.zipWithIndex) {
      var twentyFourBit = 0
      for ((ch, i) <- group.zipWithIndex) {
        val sextet = sixtyFour.indexOf(ch)
        if (sextet < 0) throw new IllegalArgumentException("Not a base64 string")
        val shifted = (sextet << (6 * (3-i)))
        twentyFourBit += shifted
      }
      val p = j * 3
      arr(p) = ((twentyFourBit >> 16) & 255).toByte
      arr(p + 1) = ((twentyFourBit >> 8) & 255).toByte
      arr(p + 2) = (twentyFourBit & 255).toByte
    }
    arr
  }
}