

package com.huxh.apps.core.notifications

import javax.inject.Inject

/**
 * Implementation of [Notifier] which does nothing. Useful for tests and previews.
 */
internal class NoOpNotifier @Inject constructor() : Notifier {
    override fun postNewsNotifications() = Unit
}
