import { WebPlugin } from '@capacitor/core';

import type { TruPluginIonicCapacitorPlugin } from './definitions';

export class TruPluginIonicCapacitorWeb
  extends WebPlugin
  implements TruPluginIonicCapacitorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async check(url: string): Promise<string> {
    return url;
  }
  async checkWithTrace(url: string): Promise<string> {
    return '';
  }
  async isReachable(): Promise<string> {
    return '';
  }
}
