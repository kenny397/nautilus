package com.jun.nautilus.domain.impl

import com.jun.nautilus.domain.App
import com.jun.nautilus.domain.Notification
import java.time.Instant

class  NotificationImpl(
    override val id: String,
    override val title: String,
    override val content: String,
    override val publishedAt: Instant = Instant.now(),
    override val app: com.jun.nautilus.domain.App,
    override val createdAt: Instant,
    override val active: Boolean
) : Notification.Base() {
    constructor(id: String,title:String,content:String,publishedAt: Instant,app: App):
            this
                (id = id,
                title = title,
                content = content,
                publishedAt = publishedAt,
                app = app,
                createdAt = Instant.now(),
                active = true
            )

}