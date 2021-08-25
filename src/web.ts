import { WebPlugin } from '@capacitor/core';

import type { Result, TruPluginIonicCapacitorPlugin } from './definitions';

export class TruPluginIonicCapacitorWeb
  extends WebPlugin
  implements TruPluginIonicCapacitorPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
  async check(options: { url: string }): Promise<Result> {
    console.log(options.url);
    return { result: '' };
  }
  async checkWithTrace(options: { url: string }): Promise<Result> {
    console.log(options.url);
    return { result: '' };
  }
  async isReachable(): Promise<Result> {
    return { result: '' };
  }
}
