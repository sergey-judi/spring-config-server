package com.judi.configuration;

import com.judi.properties.CustomVaultProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.cloud.context.encrypt.EncryptorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Data
@Configuration
@RequiredArgsConstructor
public class EncryptionConfiguration {

  //  @Value("${customkey}")
  public String customKey;

//  @Bean
//  public TextEncryptor textEncryptor(
//      CustomVaultProperties vaultProperties,
//      KeyProperties keyProperties
//  ) {
//    System.out.println(customKey);
////    VaultResponse read = vaultTemplate.read("/secret/data/spring-config-server");
////    System.out.println(read.getRequiredData());
////    read = vaultTemplate.read("/kv/spring-config-server");
////    System.out.println(read.getRequiredData());
//
//    System.out.println(vaultProperties);
//    System.out.println(keyProperties);
//
//    return new EncryptorFactory(
//        keyProperties.getSalt()
//    ).create(vaultProperties.key());
//  }

}
