package com.qksoft.cockroachdemo.Sequence;

import com.github.rholder.fauxflake.DefaultIdGenerator;
import com.github.rholder.fauxflake.api.IdGenerator;
import com.github.rholder.fauxflake.provider.SystemTimeProvider;
import com.github.rholder.fauxflake.provider.twitter.SnowflakeEncodingProvider;
import com.qksoft.cockroachdemo.util.crypto.Randomness;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class FlakeSequence implements IdentifierGenerator {
    IdGenerator idGenerator;
    public FlakeSequence(){
        long mid = Randomness.randomIntSecure(1024); // TODO(gburd): use a hash of the hostname?
        idGenerator = new DefaultIdGenerator(new SystemTimeProvider(), new SnowflakeEncodingProvider(mid));
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        while (true) {
            try {
                return idGenerator.generateId(10).asLong();
            } catch (InterruptedException e) {
                // We waited more than 10ms to generate an Id, try again.  This could be due to NTP
                // drift, leap seconds, GC pause, who knows.
            }
        }
    }
}
