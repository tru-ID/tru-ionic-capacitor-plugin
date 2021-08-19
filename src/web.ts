import { WebPlugin } from '@capacitor/core';

import type { Result, TruPluginIonicCapacitorPlugin } from './definitions';

export class TruPluginIonicCapacitorWeb
  extends WebPlugin
  implements TruPluginIonicCapacitorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async check(url: string): Promise<Result> {
    console.log(url);
    return { result: '' };
  }
  async checkWithTrace(url: string): Promise<Result> {
    console.log(url);
    return { result: '' };
  }
  async isReachable(): Promise<Result> {
    return { result: '' };
  }
}
