# salt-encrypt

A tiny module for salting and encrypting passwords, returning Base64 strings. It is written in Scala, and also callable from Java or other JVM languages.

`Encrypt` just configures and calls into `javax.crypto.SecretKeyFactory`, 
to save having to write the boilerplate in every project that generates salts and hashes passwords.

The key factory used is `PBKDF2WithHmacSHA1`, with the random number generator `SHA1PRNG`.

Salt-encrypt also includes its own `Base64` encoder to deal with converting the binary
arrays that `javax.crypto.SecretKeyFactory` returns into strings.  It includes its own Base64 code, rather than importing a Base64 module, to avoid version conflicts if your web framework also depends on a Base64 library.

### To generate a salt

    val salt = Encrypt.genSaltB64
    
This generates a 12 byte salt encoded as a Base64 string
    
### To hash a password

    val hash = Encrypt.encrypt(salt, password)     
    
This generates a 192 bit hash, using one thousand iterations of the `PBKDF2WithHmacSHA1` hashing algorithm, and returns it as a Base64 encoded string.

### To encode a byte array to Base64

    val str = Base64.encode(arr)

The byte array length must be divisible by 3 (or it will throw an IllegalArgumentException).  

### To decode a Base64 string

    val byteArr = Base64.decode(base64string)

The string length should be divisible by 4 (or it will throw an IllegalArgumentException).

### History

salt-encrypt is part of the "handy" libraries used for The Intelligent Book, Impressory, Assessory, Awarinator, and a few of Will Billingsley's other projects at NICTA.

### Licence

MIT Licence..

### Feature requests

Feel free to raise Issues on GitHub. However the code is very short (less than 100 lines of code), so you might want to just fork the repository and edit the code yourself.

