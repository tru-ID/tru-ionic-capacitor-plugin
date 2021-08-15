import { registerPlugin } from '@capacitor/core';

import type { TruPluginIonicCapacitorPlugin } from './definitions';

const TruPluginIonicCapacitor = registerPlugin<TruPluginIonicCapacitorPlugin>('TruPluginIonicCapacitor', {
  web: () => import('./web').then(m => new m.TruPluginIonicCapacitorWeb()),
});

export * from './definitions';
export { TruPluginIonicCapacitor };
