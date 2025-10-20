package com.example.partnerkin.data.models.utils

import com.example.partnerkin.data.models.Status
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


object StatusSerializer : KSerializer<Status> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Status", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Status) {
        encoder.encodeString(
            when (value) {
                Status.CANCELED -> "canceled"
                Status.PUBLISH -> "publish"
            }
        )
    }

    override fun deserialize(decoder: Decoder): Status {
        return when (decoder.decodeString()) {
            "canceled" -> Status.CANCELED
            "publish" -> Status.PUBLISH
            else -> Status.PUBLISH
        }
    }
}