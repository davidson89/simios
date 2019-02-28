package br.com.mercadolivre.simios.domain.persistence;

import java.util.List;
import java.util.zip.CRC32;

final class HashUtil {

    static Long getHash(String[] dnaSequence) {
        StringBuilder result = new StringBuilder();
        List.of(dnaSequence).forEach(result::append);

        CRC32 crc32 = new CRC32();
        crc32.update(result.toString().getBytes());
        return crc32.getValue();
    }

}
