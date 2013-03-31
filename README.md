# salt-encrypt

A tiny module for salting and encrypting passwords, returning Base64 strings

    val hash = Encrypt.encrypt(salt, password)        

`Encrypt` just configures and calls into `javax.crypto.SecretKeyFactory`, 
to save having to write the boilerplate in every project that hashes passwords.

It also includes its own `Base64` encoder to deal with converting the binary
arrays tht `javax.crypto.SecretKeyFactory` returns into strings. 

(It includes its own Base64 code, rather than importing a Base64 module, to avoid 
version conflicts if your web framework also depends on a Base64 library.)

