package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification
import java.time.Instant

internal interface MutableNotification: Notification{

    override val id: String

    override var title: String

    override var content: String

    override val createdAt: Instant

    override var publishedAt: Instant

    override var active: Boolean

    override val app: com.jun.nautilus.domain.App
}
internal class MutableNotificationImpl(
    override val id: String,
    override var title: String,
    override var content: String,
    override var publishedAt: Instant,
    override val app: com.jun.nautilus.domain.App,
    override var active: Boolean,
    override val createdAt: Instant
): MutableNotification, Notification.Base(){
    constructor(notification: Notification):
            this(
                id = notification.id,
                title = notification.title,
                content= notification.content,
                createdAt= notification.createdAt ,
                app = notification.app,
                active= notification.active,
                publishedAt= notification.publishedAt
            )


}