export interface TruPluginIonicCapacitorPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
