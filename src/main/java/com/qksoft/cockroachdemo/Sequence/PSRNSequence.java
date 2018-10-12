package com.qksoft.cockroachdemo.Sequence;

import com.qksoft.cockroachdemo.util.crypto.Randomness;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class PSRNSequence implements IdentifierGenerator {

    public PSRNSequence(){

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return Randomness.randomHexStringSecure(36);
    }
}
