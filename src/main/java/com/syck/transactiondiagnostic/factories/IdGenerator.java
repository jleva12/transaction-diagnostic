package com.syck.transactiondiagnostic.factories;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Supplier;

public class IdGenerator implements Supplier<String> {
    private final String prefix;

    public IdGenerator(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Retrieves a unique ID string with the specified prefix.
     *
     * @return A unique ID string with the specified prefix.
     */
    public String get() {
        return generateUniqueId(prefix);
    }

    /**
     * Generates a unique ID string with the specified prefix.
     *
     * @param prefix The prefix to be included in the generated ID.
     * @return A unique ID string with the specified prefix.
     */
    private static String generateUniqueId(String prefix) {
        UUID uuid = UUID.randomUUID();
        byte[] uuidBytes = asBytes(uuid);
        String encodedUuid = Base64.getUrlEncoder().withoutPadding().encodeToString(uuidBytes);
        // Remove any hyphens that might appear in the encoded UUID
        String sanitizedUuid = encodedUuid.replace("-", "").replace("_", "");
        return prefix + "_" + sanitizedUuid;
    }

    /**
     * Converts a UUID to a byte array.
     *
     * @param uuid The UUID to be converted.
     * @return A byte array representing the UUID.
     */
    private static byte[] asBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }
}
