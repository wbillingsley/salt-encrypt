package com.wbillingsley.encrypt;

import org.scalatest.FunSpec
import org.scalatest.BeforeAndAfter
import scala.collection.mutable.Stack

class EncryptSpec extends FunSpec with BeforeAndAfter {

  

  before {
    
  }

  describe("Encrypt") {
  
    it("should generate different salts of the same length") {
      
      val s1 = Encrypt.genSaltB64
      val s2 = Encrypt.genSaltB64
    
      assert(s1.length === 16)
      assert(s1 != s2)
    }

    it("should produce the same hash from the same salt and password") {
      
      val salt = Encrypt.genSaltB64
      val password = "my1ns3cur3Passw0Rd"
        
      val hash1 = Encrypt.encrypt(salt, password)
      val hash2 = Encrypt.encrypt(salt, password)
      
      assert(hash1.length === 32)
      assert(hash1 === hash2)
    }

    it("should produce a different same hash from a different salt or password") {
      
      val salt1 = Encrypt.genSaltB64
      val password1 = "my1ns3cur3Passw0Rd"
      val salt2 = Encrypt.genSaltB64
      val password2 = "my1ns3cur3Passw0Re"
        
      val hash11 = Encrypt.encrypt(salt1, password1)
      val hash12 = Encrypt.encrypt(salt1, password2)
      val hash21 = Encrypt.encrypt(salt2, password1)
      val hash22 = Encrypt.encrypt(salt2, password2)
      
      assert(hash11 != hash12)
      assert(hash11 != hash21)
      assert(hash11 != hash22)
      assert(hash12 != hash21)
      assert(hash12 != hash22)
      assert(hash21 != hash22)
    }
  }
}
