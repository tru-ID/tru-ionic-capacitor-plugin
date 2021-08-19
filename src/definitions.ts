export interface TruPluginIonicCapacitorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  check(url: string): Promise<Result>;
  checkWithTrace(url: string): Promise<Result>;
  isReachable(): Promise<Result>;
}
export interface Result {
  result: string;
}
