package com.group9.bankofaz.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.group9.bankofaz.model.ExternalUser;

@Service
public class UserOperationsServiceImpl implements UserOperationsService {

	@Override
	@Transactional
	public String getUploadFileLocation() {
		try {
			Random randomGenerator = new Random();
			int rand = randomGenerator.nextInt();
			if (rand < 0)
				rand *= -1;

			File temp = File.createTempFile(rand + "", ".tmp");
			return temp.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	@Transactional
	public boolean uploadFile(String location, MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(location)));
			stream.write(bytes);
			stream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean compareKeys(ExternalUser user, String privateKeyFileLocation) {
		try {
			// set the Security Provider & context
			Security.addProvider(new BouncyCastleProvider());
			Cipher rsa;
			rsa = Cipher.getInstance("RSA");

			// generate plaintext
			Random rand = new Random();
			String plaintext = rand.nextInt() + "";

			// get private and public keys
			byte[] pubKeyBytes = user.getPublickey().getBytes(1, (int) user.getPublickey().length());
			byte[] pvtKeyBytes = Files.readAllBytes(Paths.get(privateKeyFileLocation));
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubKeyBytes));
			PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(pvtKeyBytes));

			// encrypt with public key
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] cipher = rsa.doFinal(plaintext.getBytes());

			// decrypt with private key
			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] utf8 = rsa.doFinal(cipher);
			String decrypt = new String(utf8, "UTF8");

			// return result
			return plaintext.equals(decrypt);
		} catch (Exception e) {
			// the decryption is not successful
			return false;
		}
	}
}
