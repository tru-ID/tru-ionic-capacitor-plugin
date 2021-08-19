export interface TruPluginIonicCapacitorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  check(url: string): Promise<string>;
  checkWithTrace(url: string): Promise<string>;
  isReachable(): Promise<string>;
}
